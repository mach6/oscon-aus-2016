package com.mycompany.myproject.sample.simple;

import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.DriverCommand;

import com.paypal.selion.platform.grid.EventListener;
import com.paypal.selion.reports.runtime.SeLionReporter;

public class DemoEventFiringListener implements EventListener {
    public void beforeEvent(Command command) {
        if (command.getName().equalsIgnoreCase(DriverCommand.CLICK)) {
            System.out.println("Before click");
        }
    }

    public void afterEvent(Command command) {
        if (command.getName().equalsIgnoreCase(DriverCommand.CLICK)) {
            System.out.println("After click");
        }
    }
}
