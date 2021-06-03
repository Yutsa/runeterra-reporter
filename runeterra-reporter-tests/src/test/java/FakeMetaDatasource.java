import java.util.*;

import com.runeterrahelper.decks.DeckWithDataFactory;
import com.runeterrahelper.meta.datasources.MetaDatasource;
import com.runeterrahelper.meta.processors.model.DeckMetaStat;

public class FakeMetaDatasource implements MetaDatasource {
    private final Set<DeckMetaStat> decksPlayed = new HashSet<>();
    private final DeckWithDataFactory deckWithDataFactory;

    public FakeMetaDatasource(DeckWithDataFactory deckWithDataFactory) {
        this.deckWithDataFactory = deckWithDataFactory;
    }

    @Override
    public Set<DeckMetaStat> retrieveDecks() {
        return decksPlayed;
    }

    public void addDeck(String deckCode, int numberOfGames, int winrate) {
        decksPlayed.add(new DeckMetaStat(deckWithDataFactory.fromCode(deckCode), numberOfGames, winrate));
    }
}
