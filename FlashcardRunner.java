public class FlashcardRunner {
    public static void main(String[] args) {
        CardCreator.extractAllSaves();
        CardCreator.printAllDeckNames();
        StudyUI studyUI = new StudyUI();
    }
}
