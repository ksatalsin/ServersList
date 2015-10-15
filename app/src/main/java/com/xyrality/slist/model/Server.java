package com.xyrality.slist.model;

import java.io.Serializable;

public class Server implements Serializable{

  /*  country = ISO country code string
    id = a unique identifier
            language = ISO language code string
            mapURL = A URL to a map server
            name = Name string of the world
    url = A URL to the world server
    worldStatus */

    String country;
    String language;
    String mapURL;
    String name;
    Long id;
    String url;
    WorldStatus worldStatus;


}
