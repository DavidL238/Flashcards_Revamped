import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;

public class CardImporter extends CardCreator{
    private final URL WEBURL;
    private String site;
    private boolean success;

    public CardImporter (URL webURL) throws Exception {
        super();
        this.WEBURL = webURL;
        success = importCards();
    }

    public boolean importCards() throws Exception {
        URLConnection urlConnect = WEBURL.openConnection();
        urlConnect.addRequestProperty("User-Agent", "Mozilla/4.76");
        int websiteType = siteType();
        String attribute = "";
        String tag = "";
        switch (websiteType) {
            case 1 -> {
                attribute = "<span class=\"TermText notranslate lang-en\">";
                tag = "</span>";
                site = "Quizlet";
                try {
                    String topic = findElement("<div class=\"SetPage-titleWrapper\"><h1>", "</h1>");
                    String author = findElement("<span class=\"UserLink-username\">", "</span>");
                    String fileName = topic + "_" + author + "_" + site;
                    super.setNameOfSet(fileName);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                attribute = "<div class=\"front_text card_text\">";
                tag = "</div>";
                site = "Cram";
                try {
                    String topic = findElement("<input type=\"hidden\" id=\"setURLLink\" value=\"www.cram.com/flashcards/", "\"");
                    System.out.println(topic);
                    super.setNameOfSet(topic + "_" + site);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case -1 -> {
                return false;
            }
        }
        try {
            InputStreamReader input = new InputStreamReader(urlConnect.getInputStream());
            BufferedReader bR = new BufferedReader(input);
            ArrayList<String> cramTerms = new ArrayList<>();
            ArrayList<String> cramDefinitions = new ArrayList<>();
            String line;
            while ((line = bR.readLine()) != null) {
                int idxStart = line.indexOf(attribute);
                while (idxStart != -1) {
                    line = line.substring(idxStart + attribute.length());
                    int idxEnd = line.indexOf(tag);
                    while (idxEnd == -1) {
                        line = line + bR.readLine();
                        idxEnd = line.indexOf(tag);
                    }
                    String word = line.substring(0,idxEnd);
                    word = removeHTMLEntity(word, "<br>");
                    word = removeHTMLEntity(word, "<p>");
                    word = removeHTMLEntity(word, "</p>");
                    word = removeHTMLEntity(word, "  ");
                    word = replaceHTMLEntity(word, "&quot;", "\"");
                    while (word.startsWith(" ")) {
                        word = word.substring(1);
                    }
                    if (websiteType == 1) {
                        super.addNewCard(word);
                    }
                    else {
                        cramTerms.add(word);
                    }
                    idxStart = line.indexOf(attribute);
                }
            }
            if (websiteType == 2) {
                bR = new BufferedReader(input);
                attribute = "<div class=\"back_text card_text\">";
                while ((line = bR.readLine()) != null) {
                    int idxStart = line.indexOf(attribute);
                    line = line.substring(idxStart + attribute.length());
                    int idxEnd = line.indexOf(tag);
                    while (idxEnd == -1) {
                        line = line + bR.readLine();
                        idxEnd = line.indexOf(tag);
                    }
                    String word = line.substring(0,idxEnd);
                    System.out.println("DEF" + word);
                    word = removeHTMLEntity(word, "<br>");
                    word = removeHTMLEntity(word, "<p>");
                    word = removeHTMLEntity(word, "</p>");
                    word = removeHTMLEntity(word, "  ");
                    word = replaceHTMLEntity(word, "&quot;", "\"");
                    while (word.startsWith(" ")) {
                        word = word.substring(1);
                    }
                    cramDefinitions.add(word);
                }
                System.out.println(cramDefinitions.size());
                for (int i = 0; i < cramDefinitions.size(); i++) {
                    super.addNewCard(cramTerms.get(i), cramDefinitions.get(i));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed. Improper Link.");
        }
        return true;
    }

    public int siteType() {
        String url = WEBURL.toString().toLowerCase();
        if (url.contains("quizlet")) {
            return 1;
        }
        else if (url.contains("cram")) {
            return 2;
        }
        return -1;
    }

    public String findElement (String openTag, String closingTag) throws Exception {
        URLConnection urlConnect = WEBURL.openConnection();
        urlConnect.addRequestProperty("User-Agent", "Mozilla/4.76");
        InputStreamReader input = new InputStreamReader(urlConnect.getInputStream());
        BufferedReader bR = new BufferedReader(input);
        String name;
        String found = "";
        int idx = -1;
        while ((name = bR.readLine()) != null) {
            idx = name.indexOf(openTag);
            if (idx != -1) {
                found = name;
                break;
            }
        }
        found = found.substring(idx + openTag.length());
        return found.substring(0, found.indexOf(closingTag));
    }

    public boolean isSuccessful() {
        return success;
    }

    public String removeHTMLEntity(String input, String entity) {
        while (input.contains(entity)) {
            int htmlIdx = input.indexOf(entity);
            input = input.substring(0, htmlIdx) + input.substring(htmlIdx + entity.length());
        }
        return input;
    }

    public String replaceHTMLEntity(String input, String entity, String replaceChar) {
        while (input.contains(entity)) {
            int htmlIdx = input.indexOf(entity);
            input = input.substring(0, htmlIdx) + replaceChar + input.substring(htmlIdx + entity.length());
        }
        return input;
    }

}
