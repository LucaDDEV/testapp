Feature: Valori Nutrizionali
  Scenario: Verifica valori nutizionali
  As a user dovrei vedere i valori nutrizionali corrispondenti dell'ingrediente selezionato.

    Given I am in the app main page
    And Tap on top recipe "Kiwi Cutie"
    And Tap on ingredient "Kiwi"
    And Tap on info
    Then I can see nutritional value
    | Nome        | Valore    | Index Valore    |
    | Sodium      | 5mg       | 1911            |
    | Protein     | 1.4g      | 1911            |
