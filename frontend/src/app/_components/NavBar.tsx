import Link from "next/link";
import {Button} from "@/components/ui/button";

export default function NavBar() {
    return (
        <nav className="bg-white border-gray-200 dark:bg-gray-900">
            <div className="max-w-screen-xl flex flex-wrap items-center justify-between mx-auto p-4">
                <a href="https://flowbite.com/" className="flex items-center space-x-3 rtl:space-x-reverse">
                    <svg className="h-8 st0" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg"
                         x="0px" y="0px" viewBox="0 0 122.88 99.56"
                    >
                        <g>
                            <path
                                d="M73.1,0c6.73,0,13.16,1.34,19.03,3.78c6.09,2.52,11.57,6.22,16.16,10.81c4.59,4.58,8.28,10.06,10.81,16.17 c2.43,5.87,3.78,12.3,3.78,19.03c0,6.73-1.34,13.16-3.78,19.03c-2.52,6.09-6.22,11.58-10.81,16.16 c-4.58,4.59-10.06,8.28-16.17,10.81c-5.87,2.43-12.3,3.78-19.03,3.78c-6.73,0-13.16-1.34-19.03-3.77 c-6.09-2.52-11.57-6.22-16.16-10.81l-0.01-0.01c-4.59-4.59-8.29-10.07-10.81-16.16c-0.78-1.89-1.45-3.83-2-5.82 c1.04,0.1,2.1,0.15,3.17,0.15c2.03,0,4.01-0.18,5.94-0.53c0.32,0.96,0.67,1.91,1.05,2.84c2.07,5,5.11,9.51,8.9,13.29 c3.78,3.78,8.29,6.82,13.29,8.9c4.81,1.99,10.11,3.1,15.66,3.1c5.56,0,10.85-1.1,15.66-3.1c5-2.07,9.51-5.11,13.29-8.9 c3.78-3.78,6.82-8.29,8.9-13.29c1.99-4.81,3.1-10.11,3.1-15.66c0-5.56-1.1-10.85-3.1-15.66c-2.07-5-5.11-9.51-8.9-13.29 c-3.78-3.78-8.29-6.82-13.29-8.9c-4.81-1.99-10.11-3.1-15.66-3.1c-5.56,0-10.85,1.1-15.66,3.1c-0.43,0.18-0.86,0.37-1.28,0.56 c-1.64-2.58-3.62-4.92-5.89-6.95c1.24-0.64,2.51-1.23,3.8-1.77C59.94,1.34,66.37,0,73.1,0L73.1,0z M67.38,26.12 c0-1.22,0.5-2.33,1.3-3.13c0.8-0.8,1.9-1.3,3.12-1.3c1.22,0,2.33,0.5,3.13,1.3c0.8,0.8,1.3,1.91,1.3,3.13v23.22l17.35,10.29 c1.04,0.62,1.74,1.6,2.03,2.7c0.28,1.09,0.15,2.29-0.47,3.34c-0.62,1.04-1.6,1.74-2.7,2.03c-1.09,0.28-2.29,0.15-3.33-0.47 L69.65,55.71c-0.67-0.37-1.22-0.91-1.62-1.55c-0.41-0.67-0.65-1.46-0.65-2.3V26.12L67.38,26.12z"/>
                            <path
                                className="st1"
                                d="M26.99,2.56c14.91,0,26.99,12.08,26.99,26.99c0,14.91-12.08,26.99-26.99,26.99C12.08,56.54,0,44.45,0,29.55 C0,14.64,12.08,2.56,26.99,2.56L26.99,2.56z M15.05,30.27c0.36-2.1,2.76-3.27,4.65-2.13c0.17,0.1,0.34,0.22,0.49,0.36l0.02,0.01 c0.85,0.81,1.8,1.66,2.74,2.5l0.81,0.73l9.59-10.06c0.57-0.6,0.99-0.99,1.85-1.18c2.94-0.65,5.01,2.95,2.93,5.15L26.17,38.19 c-1.13,1.2-3.14,1.31-4.35,0.16c-0.69-0.64-1.45-1.3-2.21-1.96c-1.32-1.15-2.67-2.32-3.77-3.48 C15.18,32.25,14.89,31.17,15.05,30.27L15.05,30.27z"/>
                        </g>
                    </svg>
                    <span
                        className="self-center text-2xl font-semibold whitespace-nowrap dark:text-white">Event Manager</span>
                </a>
                <button data-collapse-toggle="navbar-default" type="button"
                        className="inline-flex items-center p-2 w-10 h-10 justify-center text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600"
                        aria-controls="navbar-default" aria-expanded="false">
                    <span className="sr-only">Open main menu</span>
                    <svg className="w-5 h-5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                         viewBox="0 0 17 14">
                        <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                              d="M1 1h15M1 7h15M1 13h15"/>
                    </svg>
                </button>
                <div className="hidden w-full md:block md:w-auto" id="navbar-default">
                    <ul className="font-medium flex flex-col p-4 md:p-0 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 rtl:space-x-reverse md:mt-0 md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700">
                        <li>
                            <Link href="/"
                                  className="block py-2 px-3 text-white bg-blue-700 rounded md:bg-transparent md:text-blue-700 md:p-0 dark:text-white md:dark:text-blue-500"
                                  aria-current="page">Home</Link>
                        </li>
                        <li>
                            <Link href="/dashboard"
                                  className="block py-2 px-3 text-gray-900 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">Dashboard</Link>
                        </li>
                        <li>
                            <Button>
                                <Link href="/login"
                                      className="block py-2 px-3  rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-white md:dark:hover:text-blue-500 dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent">LOGIN</Link>
                            </Button>
                        </li>

                    </ul>
                </div>
            </div>
        </nav>

    );
};
