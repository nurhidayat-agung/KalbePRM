package callplan.prm.kalbe.callplanlibrary.ENUM;

/**
 * Created by rhezaTesar on 11/3/2016.
 */

public enum enum_VisitPlanCategory {
    OUTLET(100),
    ACTIVITY(101),
    MEETING(102),
    PERJALANAN_DINAS(103),
    LBR(104),
    TRAINING(105),
    CUTI(106);
    private final int IdVisitPlanCategory;

    enum_VisitPlanCategory(int idConfig) {
        this.IdVisitPlanCategory = idConfig;
    }
    public int getValue() {
        return this.IdVisitPlanCategory;
    }
}
