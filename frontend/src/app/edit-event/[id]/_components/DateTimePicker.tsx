import dayjs, {Dayjs} from "dayjs";
import {Dispatch, SetStateAction} from "react";
import {DateTimePicker} from "@mui/x-date-pickers";

type Props = {
    label?: string,
    setTime?: Dispatch<SetStateAction<Dayjs | null>>,
    time?: dayjs.Dayjs | null
};
export default function MDateTimePicker({label, setTime, time}: Props) {

    return (
        <DateTimePicker
            label={label}
            value={dayjs(time?.format("YYYY-MM-DDTHH:mm"))}
            onChange={(newValue) => setTime?.(newValue)}
        />
    );
};
