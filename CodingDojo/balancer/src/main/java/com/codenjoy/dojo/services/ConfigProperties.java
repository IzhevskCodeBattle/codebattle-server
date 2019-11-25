package com.codenjoy.dojo.services;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 - 2019 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.codenjoy.dojo.services.hash.Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * Тут собраны только те проперти, которые важны в контроллерах.
 * Все дело в том, что я не хочу делать второго конфига который будет уметь
 * находить properties файлы вокруг приложения еще и в spring-context.xml
 * а это потому, что он не обрабатывается фильтрами maven при сборке в war.
 * Единственное место, где конфигурится *.properties - applicationContext.xml
 */
@Component
public class ConfigProperties {

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${email.hash}")
    private String emailHash;

    @Value("${dispatcher.url.create}")
    private String urlCreatePlayer;

    @Value("${dispatcher.url.remove}")
    private String urlRemovePlayer;

    @Value("${dispatcher.url.get}")
    private String urlGetPlayers;

    @Value("${dispatcher.url.clear}")
    private String urlClearScores;

    @Value("${dispatcher.url.exists}")
    private String urlExistsPlayer;

    @Value("${dispatcher.url.enabled}")
    private String urlGameEnabled;

    @Value("${game.type}")
    private String gameType;

    @Value("${game.room}")
    private int gameRoom;

    @Value("${game.final.time}")
    private String gameFinalTime;

    @Value("#{'${game.servers}'.split(',')}")
    private List<String> servers;

    @Value("${game.start.day}")
    private String dayStart;

    @Value("${game.end.day}")
    private String dayEnd;

    @Value("${game.finalists.count}")
    private int dayFinalistsCount;

    public void updateFrom(ConfigProperties config) {
        BeanUtils.copyProperties(config, this);
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public String getEmailHash() {
        return emailHash;
    }

    public String getUrlCreatePlayer() {
        return urlCreatePlayer;
    }

    public String getUrlRemovePlayer() {
        return urlRemovePlayer;
    }

    public String getUrlGetPlayers() {
        return urlGetPlayers;
    }

    public String getUrlClearScores() {
        return urlClearScores;
    }

    public String getUrlExistsPlayer() {
        return urlExistsPlayer;
    }

    public String getUrlGameEnabled() {
        return urlGameEnabled;
    }

    public String getGameType() {
        return gameType;
    }

    public int getGameRoom() {
        return gameRoom;
    }

    public String getGameFinalTime() {
        return gameFinalTime;
    }

    public List<String> getServers() {
        return servers;
    }

    public String getEmail(String id) {
        return Hash.getEmail(id, emailHash);
    }

    public String getId(String email) {
        return Hash.getId(email, emailHash);
    }

    public String getAdminToken() {
        return DigestUtils.md5DigestAsHex(adminPassword.getBytes());
    }

    public String getDayStart() {
        return dayStart;
    }

    public String getDayEnd() {
        return dayEnd;
    }

    public int getDayFinalistCount() {
        return dayFinalistsCount;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public void setEmailHash(String emailHash) {
        this.emailHash = emailHash;
    }

    public void setUrlCreatePlayer(String urlCreatePlayer) {
        this.urlCreatePlayer = urlCreatePlayer;
    }

    public void setUrlRemovePlayer(String urlRemovePlayer) {
        this.urlRemovePlayer = urlRemovePlayer;
    }

    public void setUrlGetPlayers(String urlGetPlayers) {
        this.urlGetPlayers = urlGetPlayers;
    }

    public void setUrlClearScores(String urlClearScores) {
        this.urlClearScores = urlClearScores;
    }

    public void setUrlExistsPlayer(String urlExistsPlayer) {
        this.urlExistsPlayer = urlExistsPlayer;
    }

    public void setUrlGameEnabled(String urlGameEnabled) {
        this.urlGameEnabled = urlGameEnabled;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setGameRoom(int gameRoom) {
        this.gameRoom = gameRoom;
    }

    public void setGameFinalTime(String gameFinalTime) {
        this.gameFinalTime = gameFinalTime;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }

    public void setDayStart(String dayStart) {
        this.dayStart = dayStart;
    }

    public void setDayEnd(String dayEnd) {
        this.dayEnd = dayEnd;
    }

    public void setDayFinalistsCount(int dayFinalistsCount) {
        this.dayFinalistsCount = dayFinalistsCount;
    }
}
