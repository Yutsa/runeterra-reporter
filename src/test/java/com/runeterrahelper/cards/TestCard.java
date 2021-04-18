package com.runeterrahelper.cards;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCard {
    @Test
    void if_a_card_is_from_the_foundation_release_set_then_the_first_two_digits_of_its_code_should_be_01() {
        // Given
        Card card = new Card(CardReleaseSet.FOUNDATION);
        // When
        String code = card.getCode();
        // Then
        assertThat(code).startsWith("01");        
    }
    
    @Test
    void if_a_card_is_from_the_rising_tides_release_set_then_the_first_two_digits_of_its_code_should_be_02() {
        // Given
        Card card = new Card(CardReleaseSet.RISING_TIDES);
        // When
        String code = card.getCode();
        // Then
        assertThat(code).startsWith("02");        
    }
    
    @Test
    void if_a_card_is_from_the_call_of_the_mountain_release_set_then_the_first_two_digits_of_its_code_should_be_03() {
        // Given
        Card card = new Card(CardReleaseSet.CALL_OF_THE_MOUNTAIN);
        // When
        String code = card.getCode();
        // Then
        assertThat(code).startsWith("03");        
    }
    
    @Test
    void if_a_card_is_from_the_empire_of_the_ascended_release_set_then_the_first_two_digits_of_its_code_should_be_04() {
        // Given
        Card card = new Card(CardReleaseSet.EMPIRE_OF_THE_ASCENDED);
        // When
        String code = card.getCode();
        // Then
        assertThat(code).startsWith("04");        
    }
}
