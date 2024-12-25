import type {Metadata} from "next";
import "./globals.css";

import {Roboto, Poppins} from 'next/font/google';
import NavBar from "@/app/_components/NavBar";
import MuiWrapper from "@/utils/MUIWrapper";
import {Toaster} from "@/components/ui/toaster";

const poppins = Poppins({
    weight: ["200", '300', "600"],
    subsets: ['latin'],
    variable: '--poppins-default'
});

const roboto = Roboto({
    weight: ["100", '300', "500"],
    subsets: ['latin'],
    variable: '--roboto-default'
});

export const metadata: Metadata = {
    title: "Event Management",
    description: "Generated by create next app",
};

export default function RootLayout({children}: Readonly<{
    children: React.ReactNode;
}>) {
    return (
        <html lang="en">
        <body
            className={`${roboto.variable} ${poppins.variable} antialiasing font-sans
            bg-[radial-gradient(hsl(0,72%,65%,40%),hsl(24,62%,73%,40%),hsl(var(--background))_60%)]
            bg-cover
            bg-no-repeat
            bg-center
            `}
        >
        <MuiWrapper>
            <NavBar/>
            {children}
        </MuiWrapper>
        <Toaster/>
        </body>
        </html>
    );
}
