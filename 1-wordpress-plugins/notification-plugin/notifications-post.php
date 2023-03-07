<?php
/**
 * @package NotificationPlugin
 */


defined('ABSPATH') or die("You can't access this directory");

class NotificationPost
{
    function register()
    {
        add_filter("the_content", array($this, "random_post_notification"), 10, 1);
    }

    //add notification to post
    function random_post_notification($content)
    {

        $notifications = get_option('notifications');
        if ($notifications) {
            $random_nr = rand(0, count($notifications) - 1);
            return '<section class="notification">' . $notifications[$random_nr] . '</section>' . $content;
        }
        return $content;

    }

}