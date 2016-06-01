package com.coolprimes.f1numbers;

/**
 * This code was generated automatically by running a query against postgrest by hitting
 * http://coolprimes.com/postgrest/wv_drivers_and_teams
 * Paste that output to http://jsonschema.net to generate a JSON Schema
 * Remember to select the Gson option (default is Jackson 2.x)
 * Take the schema and paste it into http://www.jsonschema2pojo.org
 * to produce the code below.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class TeamDriver {

    @SerializedName("driver_id")
    @Expose
    private Integer driverId;

    @SerializedName("team_id")
    @Expose
    private Integer teamId;

    @SerializedName("first_name")
    @Expose
    private String firstName;

    @SerializedName("last_name")
    @Expose
    private String lastName;

    @SerializedName("last_team")
    @Expose
    private String lastTeam;

    @SerializedName("iso2code")
    @Expose
    private String iso2code;

    public String getIso2code() {
        return iso2code;
    }

    public void setIso2code(String iso2code){
        this.iso2code = iso2code;
    }
    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastTeam() {
        return lastTeam;
    }

    public void setLastTeam(String lastTeam) {
        this.lastTeam = lastTeam;
    }
}