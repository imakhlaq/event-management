import dayjs, {Dayjs} from "dayjs";
import {Dispatch, SetStateAction} from "react";
import {DateTimePicker} from "@mui/x-date-pickers";

type Props = {
    label?: string,
    setTime?: Dispatch<SetStateAction<Dayjs | null>>,
    time?: dayjs.Dayjs | null
};
export default function MDateTimePicker({label, setTime, time}: Props) {
    let value;
    return (
        <DateTimePicker
            label={label}
            value={value}
            onChange={(newValue) => setTime?.(newValue)}
            defaultValue={dayjs(time?.format("YYYY-MM-DDTHH:mm"))}
        />
    );
};
