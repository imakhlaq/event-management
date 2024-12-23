import axios from "axios";
import {googleLogin} from "@/utils/socialLogin";

export const service = axios.create({
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true
})

service.interceptors.response.use(
    response => {

        const data = response.data;
        console.log(data)
        if (data["redirectUrl"]) googleLogin();

        return response;
    }, // Directly return successful responses.
    async error => {
        return Promise.reject(error); // For all other errors, return the error as is.
    }
);