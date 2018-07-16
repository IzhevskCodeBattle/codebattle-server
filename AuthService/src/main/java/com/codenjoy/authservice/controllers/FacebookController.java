package com.codenjoy.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping("/facebook")
public class FacebookController {

    @Autowired
    private Facebook facebook;
    @Autowired
    private ConnectionRepository connectionRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String helloFacebook(Model model){
        if ( facebook == null || !facebook.isAuthorized()){
            return "redirect:/connect/facebook";
        }

        model.addAttribute(facebook.userOperations().getUserProfile());
        PagedList homeFeed = facebook.feedOperations().getHomeFeed();
        model.addAttribute("feed", homeFeed);

        return "feeds";
    }

    @RequestMapping(value = "/profile/{facebookUser}")
    public String getUserProfile(@PathVariable String facebookUser, Model model) {
        FacebookProfile userProfile = facebook.userOperations().getUserProfile(facebookUser);
        model.addAttribute("userProfile", userProfile);

        return "profile";
    }
}
