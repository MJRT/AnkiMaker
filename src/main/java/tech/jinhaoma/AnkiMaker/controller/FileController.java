package tech.jinhaoma.AnkiMaker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.jinhaoma.AnkiMaker.common.TxtUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by mjrt on 1/30/2017.
 */
@Controller
@ResponseBody
@RequestMapping(value = "/file")
public class FileController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String upload(@RequestParam("file")MultipartFile file){
        List<String> txt = null;
        if(!file.isEmpty()){
            try {
                txt = TxtUtils.readTxt(file,"UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
                return "error" + e.toString();
            }
            System.out.println(txt.toString());
            return "upload success!";
        } else {
            return "File is empty";
        }

    }
}
