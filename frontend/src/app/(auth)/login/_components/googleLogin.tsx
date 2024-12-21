"use client";

import {googleLogin} from "@/utils/socialLogin";

export default function GoogleLogin() {
    return (
        <div>
            <button onClick={googleLogin}><p>Login With google</p></button>
        </div>
    );
};
