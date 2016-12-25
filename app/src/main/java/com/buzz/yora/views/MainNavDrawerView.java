package com.buzz.yora.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.buzz.yora.R;
import com.buzz.yora.activities.BaseActivity;
import com.buzz.yora.activities.ContactsActivity;
import com.buzz.yora.activities.MainActivity;
import com.buzz.yora.activities.ProfileActivity;
import com.buzz.yora.activities.SentMessagesActivity;
import com.buzz.yora.infrastructure.User;
import com.buzz.yora.services.Account;
import com.squareup.otto.Subscribe;

public class MainNavDrawerView  extends NavDrawerView {

    private final TextView displayNameText;
    private final ImageView avatarImage;

    public MainNavDrawerView(final BaseActivity activity) {
        super(activity);

        addItem(new ActivityNavDrawerItem(MainActivity.class, "Inbox", null, R.drawable.ic_markunread_black, R.id.include_main_nav_drawer_top_items));
        addItem(new ActivityNavDrawerItem(SentMessagesActivity.class, "Sent Messages", null, R.drawable.ic_send_black, R.id.include_main_nav_drawer_top_items));
        addItem(new ActivityNavDrawerItem(ContactsActivity.class, "Contacts", null, R.drawable.ic_group_black, R.id.include_main_nav_drawer_top_items));
        addItem(new ActivityNavDrawerItem(ProfileActivity.class, "Profile", null, R.drawable.ic_person_black, R.id.include_main_nav_drawer_top_items));

        addItem(new BasicNavDrawerItem("Logout", null, R.drawable.ic_arrow_back_black, R.id.include_main_nav_drawer_bottom_items) {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "You have logged out!", Toast.LENGTH_SHORT).show();
            }
        });

        displayNameText = (TextView) navDrawerView.findViewById(R.id.include_main_nav_drawer_display_name);
        avatarImage = (ImageView) navDrawerView.findViewById(R.id.include_main_nav_drawer_avatar);

        User loggedUser = activity.getYoraApplication().getAuth().getUser();
        displayNameText.setText(loggedUser.getDisplayName());

        // TODO change avatarImage to AvatarUrl from loggedUser
    }

    @Subscribe
    public void onUserDetailsUpdated(Account.UserDetailsUpdatedEvent event) {
        // TODO: Update Avatar URL
        displayNameText.setText(event.user.getDisplayName());
    }
}
