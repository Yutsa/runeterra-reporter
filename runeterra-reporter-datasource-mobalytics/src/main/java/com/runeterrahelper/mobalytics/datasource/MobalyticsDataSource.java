package com.runeterrahelper.mobalytics.datasource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.runeterrahelper.cards.repository.CardRepository;
import com.runeterrahelper.meta.datasources.MetaDatasource;
import com.runeterrahelper.meta.processors.model.DeckMetaStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class MobalyticsDataSource implements MetaDatasource {
    private final HttpClient httpClient;
    private final CardRepository cardRepository;
    Logger logger = LoggerFactory.getLogger(MobalyticsDataSource.class);

    public MobalyticsDataSource(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
        this.httpClient = HttpClient.newBuilder()
                .version(Version.HTTP_2)
                .followRedirects(Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    @Override
    public Set<DeckMetaStat> retrieveDecks() {
        DeckMetaStatFactory deckMetaStatFactory = new DeckMetaStatFactory(cardRepository);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(createURI())
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
            MobalyticsDeckStatResponse mobalyticsDeckStatResponse = new ObjectMapper().readValue(response.body(),
                    MobalyticsDeckStatResponse.class);
            return mobalyticsDeckStatResponse.getDecksStats()
                    .stream()
                    .map(deckMetaStatFactory::createDeckMetaStat).collect(Collectors.toSet());
        } catch (IOException | InterruptedException e) {
            logger.error("Error getting decks stats from Mobalytics.");
            logger.error(e.getMessage());
            return Collections.emptySet();
        }
    }

    private URI createURI() {
        return URI.create("http://lor.mobalytics.gg/api/v2/meta/statistics/decks?sortBy=matchesDesc&from=0&count=200&rank=master&rank=diamond&rank=platinium&threshold=all");
    }
}
