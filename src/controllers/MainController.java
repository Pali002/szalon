/*
* File: MainController.java
* Author: Zentai Pál
* Copyright: 2023, Zentai Pál
* Group: Szoft-II-N
* Date: 2023-01-30
* Github: https://github.com/Pali002/
* Licenc: GNU GPL
*/

package controllers;

import models.Restapi;
import views.Maincon;

public class MainController {
    Maincon maincon;
    Restapi restapi;

    public MainController() {
        this.restapi = new Restapi();
        this.maincon = new Maincon(this.restapi);
    }
    
}
