import dayjs, {Dayjs} from "dayjs";
import {Dispatch, SetStateAction} from "react";
import {DateTimePicker} from "@mui/x-date-pickers";

type Props = {
    label?: string;
    setTime?: Dispatch<SetStateAction<Dayjs | null>>;
    time?: dayjs.Dayjs | null;
};
export default function MDateTimePicker({label, setTime, time}: Props) {
    return (
        <DateTimePicker
            sx={{
                fontSize: '1.1rem',
                textDecorationColor: "black",
                fontWeight: "500",
                input: {backgroundColor: 'white', marginTop: '5px', fontSize: '1.1rem', textDecorationColor: "black"},
                svg: {marginTop: '10px'},
            }}
            className="text-2xl"
            label={label}
            value={dayjs(time?.format("YYYY-MM-DDTHH:mm"))}
            onChange={(newValue) => setTime?.(newValue)}
        />
    );
}










