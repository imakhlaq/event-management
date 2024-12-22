import {service} from "@/utils/service";
import {CalenderEvents} from "../../types";

export async function fetchEvents(month?: string, year?: string) {

    let url = process.env.NEXT_PUBLIC_BASEURL + "/apiv1/events/get-events";
    console.log(url)
    if (url === undefined) return;
    if (month !== undefined && year !== undefined) url = `${url}?year=${year}&month=${month}`
    return await service.get<CalenderEvents>(url);
}