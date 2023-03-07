<?php
/**
 * @package NotificationPlugin
 * Plugin Name: Notifications
 * Plugin URI: https://example.com/plugins/NotificationPlugin/
 * Description: Add notifications to posts.
 * Version: 1.0
 * Requires at least: 5.0
 * Requires PHP: 7.2
 * Author: Maria Markowiak, Paulina Drzazga
 * Author URI: https://github.com/MarkowiakM/advanced-web-technologies
 * License: GPL v2 or later
 * License URI: https://www.gnu.org/licenses/gpl-2.0.html
 */


defined('ABSPATH') or die("You can't access this directory");

include('notifications-admin-panel.php');
include('notifications-post.php');

class Notification
{
    function __construct()
    {
        add_action('init', array($this, 'custom_post_type'));
    }

    function register()
    {
        add_action('wp_enqueue_scripts', array($this, 'register_wp_styles'));
        add_action('admin_enqueue_scripts', array($this, 'register_admin_styles'));
    }


    function activate()
    {
        $this->custom_post_type();
        flush_rewrite_rules();
    }

    function deactivate()
    {
        flush_rewrite_rules();
    }

    function register_wp_styles()
    {
        wp_register_style('wp-styles', plugins_url('/css/wp-style.css', __FILE__));
        wp_enqueue_style('wp-styles');
    }

    function register_admin_styles()
    {
        wp_register_style('admin-styles', plugins_url('/css/admin-style.css', __FILE__));
        wp_enqueue_style('admin-styles');
    }

    function custom_post_type()
    {
        register_post_type('book', ['public' => true, 'label' => 'Books']);
    }

}


$notificationPlugin = new Notification();
$adminPanel = new NotificationAdminPanel();
$notificationPost = new NotificationPost();

$notificationPlugin->register();
$adminPanel->register();
$notificationPost->register();



//activation
register_activation_hook('__FILE__', array($notificationPlugin, 'activate'));

//deactivation
register_deactivation_hook('__FILE__', array($notificationPlugin, 'deactivate'));