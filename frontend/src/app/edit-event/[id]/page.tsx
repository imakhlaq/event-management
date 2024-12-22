"use client"
import {fetchEeventById} from "@/utils/fetchingService";
import {useEffect} from "react";

type Props = {
    params: Promise<{ id: string }>;
};
export default function Page({params}: Props) {

    useEffect(() => {
        (async function () {
            const data = await fetchEeventById((await params).id);
            console.log(data.data)
        })()
    }, [])

    return (
        <div>$</div>
    );
};
