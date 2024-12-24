"use client"

import {googleLogin} from "@/utils/socialLogin";
import {Button} from "@/components/ui/button";
import {FaGoogle} from "react-icons/fa";

export default function GoogleLogin() {
    return (
        <div className="h-screen flex justify-center">
            <Button
                className="mt-[40vh]" onClick={googleLogin}>
                <FaGoogle/>
                <span>Login With google</span></Button>
        </div>
    );
};
