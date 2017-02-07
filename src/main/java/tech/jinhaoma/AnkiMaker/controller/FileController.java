package tech.jinhaoma.AnkiMaker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import tech.jinhaoma.AnkiMaker.common.TxtUtils;
import tech.jinhaoma.AnkiMaker.domain.WordCard;
import tech.jinhaoma.AnkiMaker.service.WordCardService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjrt on 1/30/2017.
 */
@Controller
@ResponseBody
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    WordCardService wordCardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String upload(@RequestParam("file")MultipartFile file){
        List<String> txt = null;
        if(!file.isEmpty()){
            try {
                System.out.println("File uploaded");
                txt = TxtUtils.readTxt(file,"UTF-8");
                List<WordCard> cards = wordCardService.batchQuery(txt);
                List<String> res = new ArrayList<>();
                for (WordCard card : cards){
                    res.add(card.toString());
                }
                TxtUtils.writeTxt("E:\\temp\\spring-boot\\card.txt",res,"UTF-8");

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
    public void testDownload(HttpServletResponse res) throws IOException{
        String fileName="card.txt";
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=card.txt");
        File file=new File("E:\\temp\\spring-boot\\card.txt");

        FileOutputStream fos=new FileOutputStream(file);
        res.setContentLengthLong(file.length());
        fos.close();
    }
}
