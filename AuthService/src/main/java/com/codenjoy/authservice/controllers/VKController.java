package com.codenjoy.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.api.VKontakteProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vkontakte")
public class VKController {

    @Autowired
    private VKontakte vKontakte;
    @Autowired
    private ConnectionRepository connectionRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String helloVk(Model model){
        if (vKontakte == null || !vKontakte.isAuthorized()){
            return "redirect:/connect/vkontakte";
        }

        model.addAttribute(vKontakte.usersOperations().getProfile());
        return "hello";
    }

    @RequestMapping(value = "/profile/{vkUser}")
    public String getUserProfile(@PathVariable String vkUser, Model model) {
        List<String> users = new ArrayList<String>();
        users.add(vkUser);
        VKontakteProfile userProfile = vKontakte.usersOperations().getProfiles(users).get(0);
        model.addAttribute("userProfile", userProfile);

        return "profile";
    }
}
