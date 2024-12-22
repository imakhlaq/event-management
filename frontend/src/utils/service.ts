import axios from "axios";

export const service = axios.create({
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true
})