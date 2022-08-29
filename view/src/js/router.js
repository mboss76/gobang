import Login from "@/components/Login";
import Gobang from "@/components/Gobang";
import Person from "@/components/Person";
import TexasPoke from "@/components/TexasPoke";
import global from "@/js/global";
function verifyLogin(to){
    if(global.username===''){
        return {path:'/login'}
    }
}
const routes=[
    {path: '/',component: Login},
    {path:'/login',component: Login},
    {
        path: '/gobang',
        component: Gobang,
        beforeEnter:[verifyLogin],
    },
    {
        path: '/person',
        component: Person,
        beforeEnter:[verifyLogin]
    },
    {
        path:'/texasPoke',
        component: TexasPoke,
        beforeEnter:[verifyLogin],
    }
]
export {
    routes
}