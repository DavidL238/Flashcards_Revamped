import java.util.ArrayList;

public class CardShuffler {
    private CardCreator cardCreator;
    private ArrayList<String> randTerms;
    private ArrayList<Integer> randIndexes;
    private ArrayList<ArrayList<String>> answerChoices;
    private final int sizeOfDeck;
    private int score;

    public CardShuffler(CardCreator cardCreator) {
        this.cardCreator = cardCreator;
        randTerms = new ArrayList<>();
        randIndexes = new ArrayList<>();
        answerChoices = new ArrayList<>();
        sizeOfDeck = cardCreator.getTerms().size();
        if (sizeOfDeck >= 4) {
            shuffleDeck();
        }
    }

    public void shuffleDeck() {
        score = 0;
        for (int i = 0; i < sizeOfDeck; i++) {
            int rand = (int)(Math.random()*sizeOfDeck);
            randIndexes.add(rand);
        }
        initializeRandTerms();
        initializeAnswers();
    }

    private void initializeRandTerms() {
        for (int indexes : randIndexes) {
            randTerms.add(cardCreator.getTerms().get(indexes));
        }
    }

    private void initializeAnswers() {
        for (int i = 0; i < randIndexes.size(); i++) {
            ArrayList<String> choices = new ArrayList<>();
            ArrayList<Integer> usedIndexes = new ArrayList<>();
            int indexOfFirstTerm = randIndexes.get(i);
            usedIndexes.add(indexOfFirstTerm);
            while (usedIndexes.size() < 4) {
                int rand = (int) (Math.random() * sizeOfDeck);
                boolean answerUsed = false;
                for (Integer usedIndex : usedIndexes) {
                    if (usedIndex == rand) {
                        answerUsed = true;
                        break;
                    }
                }
                if (!answerUsed) {
                    int placement = (int) (Math.random() * 2);
                    usedIndexes.add(placement, rand);
                }
            }
            for (int idx : usedIndexes) {
                choices.add(cardCreator.getDefinitions().get(idx));
            }
            answerChoices.add(choices);
        }
    }

    public boolean selectAnswer(String choice) {
        answerChoices.remove(0);
        randTerms.remove(0);
        int index = randIndexes.remove(0);
        if (cardCreator.getDefinitions().get(index).equals(choice)) {
            score += 1000;
            return true;
        }
        return false;
    }

    public String getTerm() {
        return randTerms.get(0);
    }

    public int getScore() {
        return score;
    }

    public ArrayList<String> getChoices() {
        return answerChoices.get(0);
    }


}
