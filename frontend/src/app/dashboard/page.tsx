"use client"

import axios from "axios";
import {useEffect, useState} from "react";

export default function Page() {

    const [data, setData] = useState()

    useEffect(() => {
        (async function () {
            const data = await axios.get("http://localhost:8080/apiv1/events/get-events", {withCredentials: true})
                .catch(err => console.log(err))
            setData(data?.data)
        }())
    }, [])

    return (
        <>
            <div>Dashboard</div>

            <p>
                {JSON.stringify(data)}
            </p>
        </>
    );
};
