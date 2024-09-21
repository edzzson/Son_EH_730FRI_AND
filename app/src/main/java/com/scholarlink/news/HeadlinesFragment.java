package com.scholarlink.news;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class HeadlinesFragment extends Fragment {

    private OnHeadlineClickListener callback;

    public interface OnHeadlineClickListener {
        void onHeadlineSelected(String headline, String content);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            callback = (OnHeadlineClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnHeadlineClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headlines, container, false);
        ListView listView = view.findViewById(R.id.headlines_list);

        final String[] headlines = {
                "F2 star Ollie Bearman promoted to F1 with Haas for 2025",
                "'Superhuman': Japan in awe after Shohei Ohtani makes MLB history",
                "Mahomes, Chiefs Win vs. Burrow, Bengals Is Most-Watched NFL CBS Sept. Game Since 1998",
                "MCLAREN TO MODIFY REAR WING AFTER 'MINI-DRS' CONTROVERSY",
                "NBA: Joel Embiid signs 3-year contract extension with 76ers"
        };

        final String[] contents = {
                "The 19-year-old impressed the world of Formula 1 when he stood in at late notice for the unwell Carlos Sainz at Ferrari in Saudi Arabia. The Briton scored points on debut with a superb drive to seventh.\n \n Bearman is currently competing in F2 with PREMA and took his first win of the season and fourth of his career last weekend in Austria in the Sprint Race. \n \n He dovetails those duties with his role as reserve driver for Haas and Ferrari in Formula 1. He is also completing six FP1 appearances with Haas this year, the third of which will be this weekend at Silverstone.",
                "MIAMI -- Los Angeles Dodgers superstar Shohei Ohtani made Major League Baseball history on Thursday, becoming the first player ever to record 50 home runs and 50 stolen bases in a single season.\n\n Ohtani officially established MLB's 50-50 club with a seventh-inning homer in the Dodgers' 20-4 victory over the Marlins in Miami.\n\n The win clinched the Dodgers' postseason berth -- a first for Ohtani, who never made the playoffs even as he earned two American League Most Valuable Player awards while with the Los Angeles Angels.\n" +
                        "\n" +
                        "The Dodgers had runners on the corners when Ohtani came to the plate with two outs in the seventh. He launched a curveball from Mike Baumann over the left centerfield wall.",
                "The rivalry between the Kansas City Chiefs and the Cincinnati Bengals continues to be one of the biggest draws in the NFL.\n The Chiefs and Bengals have met during the regular season in each of the last four years and faced each other in the 2021 and 2022 playoffs. Each team has won two of the four regular-season meetings, and Cincinnati upended Kansas City on its way to Super Bowl LVI while Kansas City got its revenge before winning Super Bowl LVII.\n" +
                        "\n" +
                        "Sunday's game was another back-and-forth contest that illustrated how evenly matched these two teams are. The Bengals held a six-point lead at halftime before a wild second half saw five lead changes. Chiefs kicker Harrison Butker drilled a 51-yard field goal as time expired to secure the one-point win. In the final minute, Kansas City's game-winning drive was aided by a defensive pass interference penalty that moved Mahomes and company into field-goal range.\n Mahomes had one of the worst games of his storied career, throwing for just 151 yards on 18-of-25 passing with two touchdowns and two interceptions. Star tight end Travis Kelce was limited to one catch for five yards. Still, the Chiefs showed their resiliency by escaping with the last-second win",
                "McLaren will modify its rear-wing design in the wake of the controversy over its 'mini-DRS' after a request from the FIA.\n" +
                        "\n" +
                        "With rival teams questioning the legality of the McLaren rear wing after the Azerbaijan Grand Prix, as the upper element appeared to rotate back to open up the slot gap, the FIA has been carefully looking at its design.\n" +
                        "\n" +
                        "And although there are no doubts that the wing fully complies with the current regulations, and passes all the static tests, it is understood that the FIA has requested for changes to be made.\n" +
                        "\n" +
                        "While McLaren will still be allowed to use the exact low-drag wing that it raced in Baku on the type of tracks it is designed for, it has been told that it needs to make alternations that will prevent the upper element from flexing in the manner it has.\n" +
                        "\n" +
                        "Although the demands of the FIA regulations purely relate to wings passing static load tests, there are other aspects to the wing behaviour that count too in terms of legality.",
                "Philadelphia 76ers' Joel Embiid  NBA\n" +
                        "FILE – Philadelphia 76ers’ Joel Embiid waves to Chicago Bulls fans after hitting a 3-point shot late in the second half of the team’s NBA basketball game against the Bulls of Saturday, Nov. 6, 2021, in Chicago. (AP Photo/Charles Rex Arbogast, File)\n" +
                        "\n" +
                        "PHILADELPHIA— Already an NBA MVP and an Olympic gold medalist, All-Star center Joel Embiid now has until the end of the decade to try to win his first NBA championship with the Philadelphia 76ers.\n" +
                        "\n" +
                        "With another maximum contract secured, Embiid wants to chase that title in Philly — and remain a Sixer for the rest of his career.\n" +
                        "\n" + "“Philadelphia is home,” Embiid wrote on Instagram.\n" +
                        "\n" +
                        "A seven-time NBA All-Star, Embiid and the 76ers agreed to a $193 million extension with a player option for the 2028-29 season, a person familiar with the deal said. The person spoke to The Associated Press on condition of anonymity because the terms have not yet been announced."
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, headlines);
        listView.setAdapter(adapter);

        // Handle click events
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            callback.onHeadlineSelected(headlines[position], contents[position]);
        });

        return view;
    }
}
