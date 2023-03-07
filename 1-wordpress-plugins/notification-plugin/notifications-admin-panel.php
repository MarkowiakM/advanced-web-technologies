<?php
/**
 * @package NotificationPlugin
 */


defined('ABSPATH') or die("You can't access this directory");

class NotificationAdminPanel
{
    function register()
    {
        add_action('admin_menu', array($this, 'notification_admin_actions_register_menu'));
        add_action('admin_enqueue_scripts', array($this, 'register_admin_scripts'));
    }

    //register admin panel
    function notification_admin_actions_register_menu()
    {
        add_options_page("Notifications", "Notifications", 'manage_options', "notifications", array($this, "notification_admin_page"));
        //params
        //1 - page title
        //2 - title in admin menu
        //3 - permissions
        //4 - url
        //5 - function
    }

    //admin panel page
    function notification_admin_page()
    {
        $notifications = get_option('notifications', []);
        $notifications_names = get_option('notifications_names', []);

        global $_POST;
        //add new notification
        if (isset($_POST['notification']) && isset($_POST['notification_name'])) {
            // echo 'div' . htmlentities($_POST['notification']) . htmlentities(html_entity_decode($_POST['notification'])) . htmlspecialchars($_POST['notification']) . htmlspecialchars(htmlspecialchars_decode($_POST['notification'])) . '</div>';

            array_push($notifications, str_replace('/\//', '', $_POST['notification']));
            array_push($notifications_names, $_POST['notification_name']);
            // echo '<div class="notice notice-success isdismissible"><p>New notttification added.</p></div>';
            //edit notification
        } else if (isset($_POST['notification-edit'])) {
            $notifications[$_POST['notification-nr']] = str_replace('/\//', '', $_POST['notification-edit']);
            // echo 'div' . htmlentities($_POST['notification-edit']) . html_entity_decode($_POST['notification-edit']) . htmlspecialchars($_POST['notification-edit']) . htmlspecialchars_decode($_POST['notification-edit']) . 'hello</div>';
        }
        //delete notification
        else if (isset($_POST['notification-del-nr'])) {
            array_splice($notifications, $_POST['notification-del-nr'], 1);
            array_splice($notifications_names, $_POST['notification-del-nr'], 1);
            echo '<div class="notice notice-success isdismissible"><p>Deleted notification.</p></div>';
        }
        //update options
        update_option('notifications', $notifications);
        update_option('notifications_names', $notifications_names);

        ?>
        <div class="wrap">
            <h1>Your notifications</h1>
            <div class="add_new">
                <span>Add new</span>
                <button id="add-notification-button" class=green-btn> + </button>
            </div>
            <form name="add_form" method="post" id="new-notification-form" class="new-notification-form hide page-section">
                <h2>Add new notification</h2>
                <div>
                    <input class="name-input" type="text" name="notification_name" required placeholder="Notification name">
                    <pre><textarea type="text" name="notification" required placeholder="Insert your html code here"></textarea></pre>
                    <input type="submit" value="Submit" class="submit-form green-btn">
                </div>
            </form>
            <?php
            if (!empty($notifications) && !empty($notifications_names)) {
                echo $this->notifications_list($notifications, $notifications_names);
            }
            ?>

        </div>
        <?php
    }

    //current notifications table
    function notifications_list($notifications, $names)
    {
        $html = '<ul>';
        foreach ($notifications as $key => $value) {
            $html .= '<li class="notifications-list-item page-section">';
            $html .= '<div class="notification">' . strval($key + 1) . '. ' . $names[$key];
            $html .=
                '<div class="notification-view hide" id="notification-view-' . $key . '">'
                . $value .
                '</div>';
            $html .= '<pre class="notification-code-view hide" id="notification-code-view-' . $key . '">'
                . htmlspecialchars($value) .
                '</pre>';
            $html .=
                '<form name="edit_form" method="post" class="notification-edit-view hide" id="notification-edit-view-' . $key . '">
                    <textarea type="text" name="notification-edit" required >'
                . $value .
                '</textarea> 
                    <input value="' . $key . '" name="notification-nr" class="hidden">
                    <input type="submit" value="Submit" class="submit-form green-btn">
            </form>';
            $html .= '</div><div class="notification-actions">';
            $html .= '<button class="view-btn">View</button>';
            $html .= '<button class="view-code-btn">View Code</button>';
            $html .= '<button class="edit-btn">Edit</button>';
            $html .=
                '<form name="delete_form" method="post" class="delete-form">
                    <input value="' . $key . '" name="notification-del-nr" class="hidden">
                    <button class="submit-btn" type="submit" value="Submit">Delete</button>
                </form>';
            $html .= '</div>';
            $html .= '</li>';
        }
        $html .= '</ul>';

        return $html;
    }

    function register_admin_scripts()
    {
        wp_register_script('admin-script', plugins_url('/js/admin-panel.js', __FILE__));
        wp_enqueue_script('admin-script');
    }

}