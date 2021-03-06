package com.codenjoy.dojo.web.rest.pojo;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 - 2018 Codenjoy
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

import java.util.List;

public class GameInfo {

  private List<PlayerInfo> playerInfoList;
  private String map;
  private List<Chopper> choppers;

  public GameInfo() {}

  public GameInfo(List<PlayerInfo> playerInfoList, String map) {
    this.playerInfoList = playerInfoList;
    this.map = map;
  }

  public List<PlayerInfo> getPlayerInfoList() {
    return playerInfoList;
  }

  public void setPlayerInfoList(List<PlayerInfo> playerInfoList) {
    this.playerInfoList = playerInfoList;
  }

  public String getMap() {
    return map;
  }

  public void setMap(String map) {
    this.map = map;
  }

  public List<Chopper> getChoppers() {
    return choppers;
  }

  public void setChoppers(List<Chopper> choppers) {
    this.choppers = choppers;
  }
}
