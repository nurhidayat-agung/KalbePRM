package callplan.prm.kalbe.kalbecallplanmobile.addons.util;

public class UtilFunc {
    public static boolean isStringNotNull(String s) {
        if (s != null)
        {
            return !s.trim().equals("");
        }
        return false;
    }
}
