package frontlinesms.legal

class TimeFormatter {

    private static final TWENTY_FOUR_HOUR_OFFSET = 12
    private static final HOURS_COLON_MINUTES = 5
    private static final HOURS = 2

    static def formatTime (time) {
        if (time.contains("AM")) {
               time = time.substring(0, HOURS_COLON_MINUTES)
               time = time.concat(":00")
        } else {
            def tempHours = time.substring(0, HOURS)
            int hoursVal = Integer.parseInt(tempHours) + TWENTY_FOUR_HOUR_OFFSET
            def finalTime = hoursVal + time.substring(HOURS, HOURS_COLON_MINUTES)
            time = finalTime.concat(":00")
        }
        time
    }


}
