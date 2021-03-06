package tech.jinhaoma.AnkiMaker.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jinhaoma.AnkiMaker.utils.TimeUtils;
import tech.jinhaoma.AnkiMaker.utils.TxtUtils;
import tech.jinhaoma.AnkiMaker.domain.EasyRepository;
import tech.jinhaoma.AnkiMaker.domain.EasyWord;
import tech.jinhaoma.AnkiMaker.domain.WordCard;
import tech.jinhaoma.AnkiMaker.service.WordCardService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by mjrt on 1/30/2017.
 */
@Controller
@ResponseBody
@RequestMapping(value = "/file")
@Log4j2
public class FileController {

    @Autowired
    WordCardService wordCardService;

    @Autowired
    EasyRepository easyRepository;

    private String cardPath = "E:\\temp\\spring-boot\\";

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String upload(@RequestParam("file")MultipartFile file){
        List<String> txt = null;
        if(!file.isEmpty()){
            try {
                System.out.println("File uploaded");
                txt = TxtUtils.readTxt(file,"UTF-8");
                txt = new ArrayList<>(new HashSet<>(txt));
                List<WordCard> cards = wordCardService.batchQuery(txt);
                List<String> res = new ArrayList<>();
                for (WordCard card : cards){
//                    System.out.println(card.toCard());
                    res.add(card.toCard());
                }
                String path = cardPath + "card"+TimeUtils.getNowTime()+".txt";
                TxtUtils.writeTxt(path,res,"UTF-8");
                System.out.println("cards has makd successful");
                log.info("cards has makd successful");
            } catch (IOException e) {
                e.printStackTrace();
                return "error" + e.toString();
            }

            return "upload success!";
        } else {
            return "File is empty";
        }

    }

    @PostMapping("/easy")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadEasy(@RequestParam("file")MultipartFile file){
        List<String> txt = null;
        if(!file.isEmpty()){
            try {
                System.out.println("File uploaded");
                txt = TxtUtils.readTxt(file,"UTF-8");
                List<EasyWord> easyWords = new ArrayList<>();
                for(String word : txt){
                    easyWords.add(new EasyWord(word));
                }
                easyRepository.insert(easyWords);
            } catch (IOException e) {
                e.printStackTrace();
                return "error" + e.toString();
            }

            return "upload success!";
        } else {
            return "File is empty";
        }

    }

    @GetMapping
    public void Download(HttpServletResponse res , String path) throws IOException{
        String fileName="card.txt";
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=card.txt");
        File file=new File(path);

        FileOutputStream fos=new FileOutputStream(file);
        res.setContentLengthLong(file.length());
        fos.close();
    }



    @Value("${server.db}")
    String BufferSwitch;

    @GetMapping(value = "/test")
    public String test() {
        return BufferSwitch;
    }
}
