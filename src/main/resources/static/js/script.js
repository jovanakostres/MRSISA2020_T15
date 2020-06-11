function getCookie(name) {
    var value = "; " + document.cookie;
    var parts = value.split("; " + name + "=");
    if (parts.length == 2) {
    	var s = parts.pop();
    	if(s.includes(";")){
    		return s.split(";").shift();
    	}else{
    		return s;
    	}
    }
    
}

function set_cookie(name, value) {
    document.cookie = name +'='+ value +'; Path=/;';
}
function delete_cookie(name) {
    document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

window.Event = new Vue({
    data: {isLoggedIn: false}
});

Vue.component('login-component',{
    /*template: '<ul class="navMenu" v-if="!isLoggedIn()" style="float:right"><li style="margin-right:5px"><a href="/login">Login</a></li><li><a href="/registration">Register</a></li></ul>' +
    '<p v-else id="loggedIn">{{logged_in_msg}} <span v-on:click="logOut" v-show="isLoggedIn()"><b color="blue">Logout</b></span></p>',
    */
	template: '<div style="display:none;"></div>',
	data: function(){
        return {role : "", promenaLozinke: ""};
    },
    mounted(){
        if(getCookie("access_token")){
            axios.get("/api/getUsername?access_token=" + getCookie("access_token"))
                .then(function(response){
                    //this.logged_in_msg = "Welcome back , " + response.data;
                	this.role = response.data[1];  
                	this.promenaLozinke = response.data[2];
                    window.Event.isLoggedIn = true;
                    Event.$emit('logged-in', this.role, this.promenaLozinke);
                }.bind(this))
                .catch(function(error){
                    delete_cookie("access_token");
                    return error;
                });
        }
        
        this.$root.$on('logged-out', () => {
            // your code goes here
            this.logOut()
        });
    },
    methods : {
        logOut(){
            axios.get("/api/logouts?access_token="+getCookie("access_token"))
                .then(function(response){
                    window.Event.isLoggedIn = false;
                    //this.logged_in_msg  = "Successfully logged out";
                    delete_cookie("access_token")
                    document.location.replace("/");
                }.bind(this))
        },
        isLoggedIn(){
            return window.Event.isLoggedIn;
        }
    }
});