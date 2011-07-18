package frontlinesms.legal

class TimeFormatter {

    private static final TWENTY_FOUR_HOUR_OFFSET = 12
    private static final HOURS_COLON_MINUTES = 5
    private static final HOURS = 2

    static def formatTime (time) {
        if (time.contains("AM")) {
               time = time.substring(0, HOURS_COLON_MINUTES)
             def hour = time.substring(0,HOURS);
             hour = (hour as Integer) % 12
             hour = (hour < 10) ? "0" + hour : hour + ""
            time = hour + time.substring(HOURS, HOURS_COLON_MINUTES) + ":00"
//             if(hour == "12"){
//                time="00:"+time.substring(3);
//             }
        } else {
            def hour = time.substring(0,HOURS);
            def tempHours = (hour as Integer) % 12
            int hoursVal = tempHours + TWENTY_FOUR_HOUR_OFFSET
            def finalTime = hoursVal + time.substring(HOURS, HOURS_COLON_MINUTES)
            time = finalTime.concat(":00")
        }
        time
    }


}
