package com.codenjoy.authservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
@RequestMapping("/twitter")
public class TwitterController {

    @Autowired
    private Twitter twitter;
    @Autowired
    private ConnectionRepository connectionRepository;

    @RequestMapping(method=RequestMethod.GET)
    public String helloTwitter(Model model) {
        if (twitter == null || !twitter.isAuthorized()){
            return "redirect:/connect/twitter";
        }

        model.addAttribute(twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        model.addAttribute("friends", friends);
        return "friends";
    }

    @RequestMapping(value = "/profile/{twitterUser}")
    public String getUserProfile(@PathVariable String twitterUser, Model model) {
        TwitterProfile userProfile = twitter.userOperations().getUserProfile(twitterUser);
        model.addAttribute("userProfile", userProfile);

        return "profile";
    }
}
