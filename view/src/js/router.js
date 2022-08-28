import Login from "@/components/Login";
import Gobang from "@/components/Gobang";
import Person from "@/components/Person";
import TexasPoke from "@/components/TexasPoke";

const routes=[
    {path: '/',component: Login},
    {path:'/login',component: Login},
    {path: '/gobang',component: Gobang},
    {path: '/person',component: Person},
    {path:'/texasPoke',component: TexasPoke}
]
export {
    routes
}