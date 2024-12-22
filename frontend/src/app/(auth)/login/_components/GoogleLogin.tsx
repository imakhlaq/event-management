"use client"

import {googleLogin} from "@/utils/socialLogin";

export default function GoogleLogin() {
    return (
        <div className="h-screen flex justify-center">
            <button onClick={googleLogin}><span>Login With google</span></button>
        </div>
    );
};
