package org.example.a24_09_camptocamp.controllers;

import org.example.a24_09_camptocamp.model.beans.MessageBean;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tchat")
public class TchatRestController {

    private ArrayList<MessageBean> list = new ArrayList<>();

    //http://localhost:8080/tchat/saveMessage
    //Json Attendu : {"pseudo": "toto", "message": "un message"}
    @PostMapping("/saveMessage")
    public void saveMessage(@RequestBody MessageBean message) {
        System.out.println("/saveMessage : " + message.getMessage() + " : " + message.getPseudo());
        list.add( message);
    }

    //http://localhost:8080/tchat/allMessages
    @GetMapping("/allMessages")
    public ArrayList<MessageBean> allMessages () {
        System.out.println("/allMessages ");

        //pour ne retourner que les 10 derniers
        ArrayList<MessageBean> toReturn = new ArrayList<>();

        for (int i = Math.max(list.size()-10, 0); i < list.size() ; i++) {
            toReturn.add(list.get(i));
        }

        return toReturn;
    }

    //http://localhost:8080/tchat/filter?message=toto
    @GetMapping("/filter")
    public List<MessageBean> filter (String message) {
        System.out.println("/filter");


        return list.stream().filter(m-> m.getMessage().contains(message)).toList();
    }


}
