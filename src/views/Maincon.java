/*
* File: Maincon.java
* Author: Zentai Pál
* Copyright: 2023, Zentai Pál
* Group: Szoft-II-N
* Date: 2023-01-30
* Github: https://github.com/Pali002/
* Licenc: GNU GPL
*/

package views;

import java.util.ArrayList;

import models.Restapi;
import models.Vehicles;

public class Maincon {
    Restapi restapi;
    public Maincon(Restapi restapi) {
        this.restapi = restapi;
        this.start();
    }

    public void start() {
    ArrayList<Vehicles> vehicles = this.restapi.getVehicles();
    for(Vehicles vehicle: vehicles) {
        System.out.printf("%10s %12s %6.2f\n",
        vehicle.licence,
        vehicle.brand,
        vehicle.price
        );
    }
    }
    
}
