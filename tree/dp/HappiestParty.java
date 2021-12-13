package tree.dp;

import java.util.ArrayList;
import java.util.List;

public class HappiestParty {
    /**
     * Assignment:
     * 要舉辦一個最開心的派對，如果某員工參加，其直接下屬全部不參加。
     *
     * Analyze:
     * 如果 root 參加，考慮其直接下屬們都不參加的總快樂值:
     *     root 的總快樂值為 root 自己的快樂值 + 下屬們都不參加的總快樂值
     * 如果 root 不參加，考其直接下屬們參加 or 不參加的總快樂值:
     *     root 的總快樂值為 0 + 每個下屬 max {參加, 不參加}
     */
    public int howHappy (Emploee emploee) {
        Info info = getInfo(emploee);
        return Math.max(info.showUp, info.noShow);
    }

    public static class Emploee {
        int happy;
        List <Emploee> subornates;

		public Emploee(int happy, List<Emploee> subornates) {
			this.happy = happy;
			this.subornates = subornates;
		}
    }

    public Info getInfo (Emploee emploee) {
        if (emploee.subornates == null) {
            return new Info(emploee.happy, 0);
        }

        List <Info> status = new ArrayList<>();
        for (Emploee e : emploee.subornates) {
            status.add(getInfo(e));
        }

        int showUp = emploee.happy;
        int noShow = 0;
        for (Info info : status) {
            showUp += info.noShow;
            noShow += Math.max(info.showUp, info.noShow);
        }
        return new Info (showUp, noShow);
    }

    public static class Info {
        int showUp;
        int noShow;
		public Info(int showUp, int noShow) {
			this.showUp = showUp;
			this.noShow = noShow;
		}
    }
}
