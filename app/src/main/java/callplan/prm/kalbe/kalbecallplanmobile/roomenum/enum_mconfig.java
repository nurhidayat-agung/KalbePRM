package callplan.prm.kalbe.kalbecallplanmobile.roomenum;

public enum enum_mconfig {

    API(1),
    TypeMobile(2),
    BackgroundServiceOnline(3),
    TimeOut(4),
    VISITPLAN_GEOTAGING_RADIUS(5),
    VISIT_PLAN_SIZE_SAVE_PHOTO(6),
    LAST_SYNC(6),
    LAST_RUN_SCHEDULE(7);
    private final int idConfig;

    enum_mconfig(int idConfig) {
        this.idConfig = idConfig;
    }
    public int getValue() {
        return this.idConfig;
    }

}
