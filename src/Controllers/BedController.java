package Controllers;

import Model.Bed;
import Services.BedService;

public class BedController {
    private BedService bedService;

    public BedController(BedService bedService) {
        this.bedService = bedService;
    }

    public void addBed(Bed bed) {
        bedService.addBed(bed);
    }

    public void checkAvailability() {
    }

    public void createBed() {
    }

    public void removeBed() {
    }
}