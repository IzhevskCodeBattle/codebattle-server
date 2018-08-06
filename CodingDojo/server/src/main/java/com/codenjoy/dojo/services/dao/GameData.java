package com.codenjoy.dojo.services.dao;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
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


import com.codenjoy.dojo.services.jdbc.ConnectionThreadPoolFactory;
import com.codenjoy.dojo.services.jdbc.CrudConnectionThreadPool;
import com.codenjoy.dojo.services.jdbc.MySqlConnectionThreadPoolFactory;
import com.codenjoy.dojo.services.jdbc.ObjectMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GameData {

    private CrudConnectionThreadPool pool;

    private String valueType;
    private String[] columnNames;


    public GameData(ConnectionThreadPoolFactory factory) {

        if(factory instanceof MySqlConnectionThreadPoolFactory) {
            valueType = "LONGTEXT";
            columnNames = new String[]{"`key`","`value`"};
        } else {
            valueType = "LONGTEXT";
            columnNames = new String[]{"key","value"};
        }

        pool = factory.create(
                "CREATE TABLE IF NOT EXISTS game_settings (" +
                        "game_type varchar(255), " +
                        columnNames[0]+" varchar(255), " +
                        columnNames[1]+" "+valueType+");");
    }

    public String get(final String gameType, final String key) {
        return pool.select("SELECT "+columnNames[1]+" FROM game_settings WHERE game_type = ? AND "+columnNames[0]+" = ?;",
                new Object[]{gameType, key},
                new ObjectMapper<String>() {
                    @Override
                    public String mapFor(ResultSet resultSet) throws SQLException {
                        if (resultSet.next()) {
                            return resultSet.getString(columnNames[1]);
                        } else {
                            return null;
                        }
                    }
                }
        );
    }

    public boolean exists(final String gameType, final String key) {
        return pool.select("SELECT count(*) AS count FROM game_settings WHERE game_type = ? AND "+columnNames[0]+" = ?;",
                new Object[]{gameType, key},
                new ObjectMapper<Boolean>() {
                    @Override
                    public Boolean mapFor(ResultSet resultSet) throws SQLException {
                        if (resultSet.next()) {
                            return resultSet.getInt("count") > 0;
                        } else {
                            return false;
                        }
                    }
                }
        );
    }

    public void set(final String gameType, final String key, final String value) {
        if (exists(gameType, key)) {
            pool.update("UPDATE game_settings SET "+columnNames[1]+" = ? WHERE game_type = ? AND "+columnNames[0]+" = ?;",
                    new Object[]{value, gameType, key});
        } else {
            pool.update("INSERT INTO game_settings (game_type, "+columnNames[0]+", "+columnNames[1]+") VALUES (?,?,?);",
                    new Object[] {gameType, key, value});
        }
    }
}