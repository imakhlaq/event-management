import {CalenderEvent} from "../../types";
import dayjs from "dayjs";

export function sortEventsCb(a: CalenderEvent, b: CalenderEvent) {

    const now = dayjs();
    const aStart = dayjs(a.start.dateTime.value);
    const bStart = dayjs(b.start.dateTime.value);

    if (now.isBefore(aStart) && now.isBefore(bStart)) {
        return -1
    }
    if (aStart.isBefore(bStart)) {
        return 1
    }
    return -1
}
