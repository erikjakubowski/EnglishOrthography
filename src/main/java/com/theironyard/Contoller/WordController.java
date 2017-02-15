package com.theironyard.Contoller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.theironyard.Repo.IndexRepo;
import com.theironyard.Repo.LetterRepo;
import com.theironyard.Repo.WordRepo;
import com.theironyard.entitiies.Index;
import com.theironyard.entitiies.Letter;
import com.theironyard.entitiies.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.*;

/**
 * Created by erikjakubowski on 1/30/17.
 */

@Controller
public class WordController {


    @Autowired
    WordRepo words;

    @Autowired
    IndexRepo indexing;

    @Autowired
    LetterRepo letters;



    ArrayList<String> wor = new ArrayList<>();

    public void sorting (String list, int pos, String currentString) {

        String[] spot = list.split(",");
        int loc = Integer.parseInt(spot[pos]);
        String spellings = indexing.findOne(loc).getValue();
        String [] spellingParts = spellings.split(",");
        for (String part: spellingParts){

            String guessing = currentString+part;
            if (pos+1 == spot.length) {
                wor.add(guessing);
            }else {
                sorting(list,pos+1,guessing);
            }

        }

    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getRandomWords(Model model) {


        List<Word> wrod = (List<Word>) words.findAll();
        Collections.shuffle(wrod);
        List<Word> wor = wrod.subList(0, 8);
        model.addAttribute("clickedWord", wor);


        return "htmlssss/index";


    }


    @RequestMapping(path = "/single/{word}", method = RequestMethod.GET)
    public String showDifficulty(@PathVariable("word") String word, Model model) throws IndexOutOfBoundsException{

        Word word1 = words.findFirstByWord(word);

        String[] dwd = word1.getLocations().split(",");
        float toughness = dwd.length / 13f;
        model.addAttribute("toughness", toughness * 100);

        char[] s = word.toCharArray();
        model.addAttribute("letterClickedWord", s);

        wor =new ArrayList<>();


        String list = word1.getLocations();

        sorting(list,0,"");


        Collections.shuffle(wor);
        List<String> wrdings = wor.subList(0,10);
        model.addAttribute("comboPieces", wrdings );









        //errros dad, x clicked,








        return "htmlssss/Split";
    }


    @RequestMapping(path = "/key/{letter}", method = RequestMethod.GET)
    public String indivLetter(@PathVariable("letter") String letter, Model model) {

        List<Word> here = (List<Word>) words.findAll();

        ArrayList<Word> here2 = new ArrayList<>();


        for (int l = 0; l < here.size(); l++) {
            Word word = here.get(l);
            if (word.getWord().contains(letter)) {
                here2.add(word);
            }

        }
        Collections.shuffle(here2);
        List<Word> huh = here2.subList(0, 10);
        model.addAttribute("letterWordList", huh);


        return "htmlssss/letterList";
    }

    @PostConstruct
    public String lsist() throws Exception {
        if (indexing.count() == 0) {
            File f = new File("index.csv");
            Scanner scanner = new Scanner(f);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                String[] st = str.split(";");
                Index s = new Index(Integer.parseInt(st[0]), st[1]);
                indexing.save(s);

            }

        }

        if (words.count() == 0) {
            File z = new File("wordList.csv");
            Scanner scanner = new Scanner(z);
            while (scanner.hasNext()) {
                String rts = scanner.nextLine();
                String[] st = rts.split(";");
                Word f = new Word(Integer.parseInt(st[0]), st[1], st[2]);
                words.save(f);
            }

        }

        if (letters.count() == 0) {
            File abcs = new File("EA.csv");
            Scanner scanner = new Scanner(abcs);
            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                String[] s = str.split(";");
                Letter let = new Letter(Integer.parseInt(s[0]), s[1], s[2]);
                letters.save(let);

            }
        }

        return "redirect:/";
    }

}