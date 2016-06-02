package com.prasilabs.dropme.backend.services.servlets;

import com.prasilabs.dropme.backend.debug.ConsoleLog;
import com.prasilabs.dropme.backend.logicEngines.RideLogicEngine;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by prasi on 31/5/16.
 */
public class CronJobServlet extends HttpServlet
{
    private static final String TAG = CronJobServlet.class.getSimpleName();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ConsoleLog.i(TAG, "cron job servlet started");

        RideLogicEngine.getInstance().deleteInactiveGeoRideKeys();
    }
}