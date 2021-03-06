package com.prasilabs.dropme.backend.services.gcm;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.prasilabs.constants.PushMessageJobType;
import com.prasilabs.dropme.backend.datastore.Ride;
import com.prasilabs.dropme.backend.debug.ConsoleLog;
import com.prasilabs.dropme.backend.io.RideDetail;
import com.prasilabs.dropme.backend.logicEngines.RideLogicEngine;
import com.prasilabs.gcmData.RideInfoPush;

import java.util.List;

/**
 * Created by prasi on 9/6/16.
 */
public class GcmSenderUtil
{
    private static final String TAG = GcmSenderUtil.class.getSimpleName();

    public static void sendRideInfoPushForAlert(Ride ride, List<String> gcmIDs)
    {
        try
        {
            RideDetail rideDetail = RideLogicEngine.getInstance().getRideDetail(ride.getId());

            if (rideDetail != null) {
                RideInfoPush rideInfoPush = new RideInfoPush();
                rideInfoPush.setRideID(rideDetail.getRideId());
                rideInfoPush.setDestLocation(rideDetail.getDestLoc());
                rideInfoPush.setOwnerName(rideDetail.getOwnerName());
                rideInfoPush.setOwnerPicture(rideDetail.getOwnerPicture());
                rideInfoPush.setVehicleType(rideDetail.getVehicleType());
                rideInfoPush.setOwnerPhone(rideDetail.getOwnerPhone());

                String message = new Gson().toJson(rideInfoPush);
                long id = ride.getId();

                boolean isSuccess = GcmSender.sendGcmMessage(id, message, PushMessageJobType.RIDE_FOUND_ALERT_STR, gcmIDs);

                ConsoleLog.i(TAG, "ride alert gcm status is : " + isSuccess);
            } else {
                ConsoleLog.w(TAG, " ride detail is null for id " + ride.getId());
            }

        }
        catch (Exception e)
        {
            ConsoleLog.e(e);
        }
    }
}
