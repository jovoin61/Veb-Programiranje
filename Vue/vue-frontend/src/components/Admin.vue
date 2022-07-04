<template>
         <div class = "container">
        <h1 class = "text-center">ADMIN MENI</h1>
        <table class="tabela-prikaz">
            <tbody>
                <tr>
                    <td>
                        <router-link to="/admin/korisnici">SVI KORISNICI</router-link>
                    </td>

                    <td>
                    
                    </td>
                    <td>

                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script>

    export default {
        name: 'AdminPocetna',
        props: {
            msg: String
        }
    }
</script>

<style>
</style>