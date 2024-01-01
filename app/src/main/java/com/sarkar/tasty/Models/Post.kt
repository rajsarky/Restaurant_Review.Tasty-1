package com.sarkar.tasty.Models

class Post {
    var postUrl: String = ""
    var caption:String = ""
    var uid:String=""
    var time:String = ""
    var rating:Float? = null
    constructor()
    constructor(postUrl: String, caption: String) {
        this.postUrl = postUrl
        this.caption = caption
    }

    constructor(postUrl: String, caption: String, uid: String, time: String, rating: Float) {
        this.postUrl = postUrl
        this.caption = caption
        this.uid = uid
        this.time = time
        this.rating = rating
    }

}