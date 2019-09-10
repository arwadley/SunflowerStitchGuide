package com.arwapp.sittm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Stitch.class}, version = 3, exportSchema = false)
public abstract class StitchRoomDatabase extends RoomDatabase {
    public abstract StitchDao stitchDao();

    private static volatile StitchRoomDatabase INSTANCE;

    static StitchRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (StitchRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            StitchRoomDatabase.class, "stitch_database").fallbackToDestructiveMigration().addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final StitchDao mDao;

        PopulateDbAsync(StitchRoomDatabase db) {
            mDao = db.stitchDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteStitches();
            Stitch backStitch = new Stitch("Back Stitch",
                    "Outline",
                    "The back stitch is typically one of the first stitches learned in hand embroidery. It is a basic, but versatile line stitch. Whether you plan to fill your design or leave the interior open, the back stitch is a good choice for outlining.",
                    "1.\tStarting from the back of the fabric, bring your needle through to the front one stitch-length away from where you intend the line to start.  \n\n" +
                            "2.\tPush the needle back through the fabric at your intended starting point. \n" +
                            "3.\tKeeping the same stitch length, bring your needle up through the fabric a stitch length away from where your last stitch ended.\n\n" +
                            "4.\tPush the needle through to the back at the endpoint of your last stitch. \n\n" +
                            "5.\tRepeat until you get your desired line length. \n", R.drawable.backstitch_collage, R.drawable.backstitch_final);
            mDao.insertStitch(backStitch);

            Stitch frenchKnot = new Stitch("French Knot",
                    "Knot",
                    "The French knot is a common knot stitch. It is often used for small “dots” in a design spaced out or in combination with other stitches.\n When a large number are placed closely together, they can work as a filler stitch as well. This produces a nice textured look and gives some depth to your design.",
                    "1.\tStarting from the back, bring your thread through to the front of the fabric. \n\n" +
                            "2.\tWrap the thread around the needle one to three times(depending on your preferred knot size. \n\n" +
                            "3.\tWith the thread still wrapped tightly around the needle, push the needle back through the fabric near the initial point you brought the thread up(but NOT through the same hole. \n\n" +
                            "4.\tKeep the thread taught as you pull all of the loose thread back through your loops to the back. \n",
                    R.drawable.frenchknot_collage, R.drawable.frenchknot_final);
            mDao.insertStitch(frenchKnot);

            Stitch satinStitch = new Stitch("Satin Stitch",
                    "Filler",
                    "The satin stitch is a common filler stitch. It works best in smaller areas, although with care can be used in larger areas. True to its name, the finished product should be smooth and silky. The instructions of this stitch are simple, but it can take some time to master getting the right thread tension for the threads to lay flat against the fabric. A word of caution: it is especially important with this stitch to pay attention to your fabric tension as well and make sure it is not too loose. ",
                    "1.\tBring your needle up through the fabric at the edge of the area you will be filling. \n\n" +
                            "2.\tPush the needle back through at the same place on the opposite side of your fill area. \n\n" +
                            "3.\tYour next stitch will start right beside your original stitch, so that the stitches run parallel to each other. \n\n" +
                            "4.\tKeep the thread taught, but not tight. Pulling it too tight can warp your design when you remove the hoop, but too loose and it may lose some of it’s smoothness. \n",
                    R.drawable.satinstitch_collage, R.drawable.satinstitch_final);
            mDao.insertStitch(satinStitch);

            Stitch stemStitch = new Stitch("Stem Stitch",
                    "Outline",
                    "The stem stitch is a great choice for an outline stitch or for lettering. I particularly like this stitch for expressing a curve. The stitches are more blended together than with, say, a back stitch, giving your line a more flowing appearance. ",
                    "1.\tStarting from the back of the fabric, bring the needle through to the front at the starting point of your line. \n\n" +
                            "2.\tPush the needle through to the back of the fabric one stitch length away (keep your stitches slightly longer as they will be overlapping).\n\n" +
                            "3.\tAt the midway point of your previous stitch, bring the needle back through to the front of the fabric, close to or underneath your previous stitch.\n\n" +
                            "4.\tPush the needle back through to the back one ½ stitch length further down your line, so that the two stiches are half-way overlapping. \n\n" +
                            "5.\tRepeat until you achieve your desired line length. \n\n" +
                            "6.\tNOTE: be careful to keep coming up on the same side of the line, or your curve will look messy. \n\n",
                    R.drawable.stemstitch_collage, R.drawable.stemstitch_final);
            mDao.insertStitch(stemStitch);

            Stitch seedStitch = new Stitch("Seed Stitch",
                    "Filler",
                    "The seed stitch is a filler stitch ideal for larger areas. It is used when you don’t want to fill the area completely and gives a nice textured look to the design. It can give a design a “fun” feel, because the stitches are placed at random. ",
                    "1.\tClearly define the area you intend to fill (whether by marking your pattern, or an outline stitch). \n\n" +
                            "2.\tPlace a series of back stitches at random, varying their spacing and direction. Optionally, you can also vary their length. \n\n" +
                            "3.\tYou should try to keep them from looking too “ordered.” \n",
                    R.drawable.seedstitch_collage, R.drawable.seedstitch_final);
            mDao.insertStitch(seedStitch);

            Stitch fishBoneStitch = new Stitch("Fishbone Stitch",
                    "Leaf",
                    "The fishbone stitch is a lovely stitch frequently used for filling leaves. It adds some really nice texture and variety to an embroidered leaf. You may run into some difficulty if your leaf shape is too narrow; try to give it more of a bell shape if you are having difficulty with this stitch. Try to keep your stitches close together to avoid any unnecessary gaps. ",
                    "1.\tDraw your leaf shape pattern onto your fabric. You will need the outline and a center line running long ways down the leaf shape. \n\n" +
                            "2.\tMake a small to medium length stitch running along the center line starting from the top of your leaf. \n\n" +
                            "3.\tTo fill in the leaf, you will be making alternating stitches between the sides of your leaf so that it fills evenly.\n\n" +
                            "4.\t Bring your needle up through the fabric on the outer edge of the leaf at the top, just under the beginning of your first stitch you made in step 2. \n\n" +
                            "5.\tYou will bring the needle through to the back of the fabric on the center line, just under the end of your original stitch from step 2. \n\n" +
                            "6.\tYou will then repeat steps 4-5 on the opposite side, bringing the needle up through to the front at the outer edge just under the start of your original stitch, and then back down under your last stitch on the center line. \n\n" +
                            "7.\tI find it helpful to remember that no matter what side you are stitching on, you bring the needle through to the FRONT on the outer edge, and then through to the BACK on the center line. \n\n" +
                            "8.\tRepeat steps 3-6, slowly moving down the leaf with your stitches at an angle until the leaf if filled. \n\n" +
                            "9.\tYou will notice the stitches get less angled and more flat as you get towards the bottom of the leaf; that is fine and necessary. \n",
                    R.drawable.fishbonestitch_collage, R.drawable.fishbonestitch_final);
            mDao.insertStitch(fishBoneStitch);

            Stitch splitStitch = new Stitch("Split Stitch",
                    "Outline",
                    "The split stitch is a really nice line stitch. It can be used for outlining, or you can use rows of split stitch for filling an area. It is similar to a back stitch, but gives a more connected look to the stitches. It feels more sophisticated, but without much extra work! ",
                    "1.\tBring the thread up through the front of the fabric, then back down forming one basic stitch. \n\n" +
                            "2.\tOn the second stitch, bring the thread up one stitch length away down your intended line.\n\n" +
                            "3.\tWhen pushing the needle through to the back, you will want to “split” the threads of your previous stitch. Bring the needle down in between the threads of your previous stitch, and ideally through the fibers of one of the strands of your previous stitch. \n\n" +
                            "4.\tRepeat steps 2-3 until you achieve your desired line length. \n",
                    R.drawable.splitstitch_collage, R.drawable.splitstitch_final);
            mDao.insertStitch(splitStitch);

            Stitch blanketStitch = new Stitch("Blanket Stitch",
                    "Border",
                    "The blanket stitch makes a nice border stitch. It’s straight line side it a nice defined border edge. It gets its name from its traditional use of stitching the edges of blankets. To give some variety, you can either stitch all of your interior lines the same length, or vary them to give it a different look. ",
                    "1.\tTo complete this stitch, you will essentially be making a triangle. You will have two points on your “edge” line- the straight line to make the border(A &C). Point B will be the end point of the line extending outward perpendicular from your edge line. \n\n" +
                            "2.\tBring your thread up at the end of the line you intend to stitch(A).\n" +
                            "3.\tNext, bring your needle down through the fabric at point (B). This should be one stitch length on your edge over, and extended to your desired length from your “edge” line. DO NOT pull the thread taught, leave plenty of slack. \n\n" +
                            "4.\tBring your needle back up through the fabric one stitch length over(C) and parallel to point(B). The needle should go up through your loop. \n\n" +
                            "5.\tPull taught. \n\n" +
                            "6.\tFor subsequent stitches, you will just need to repeat steps 3-5 until you achieve your desired “edge” line length. \n\n",
                    R.drawable.blanketstitch_collage, R.drawable.blanketstitch_final);
            mDao.insertStitch(blanketStitch);

            Stitch runningStitch = new Stitch("Running Stitch",
                    "Outline",
                    "The running stitch is typically one of the most basic hand stitches in sewing and embroidery. It is a basic line stitch and can be used easily for straight or curved lines. While a simple stitch, I think it gives your design a bit of whimsy and fun when used as an outline. Varying your spacing, you could also use it in rows as a filler stitch. The key to a good running stitch is to keep your stitching even. Try to keep your stitches and spacing as close to uniform as possible. Also pay attention to your stitches themselves- short to medium length stitches tend to work best for keeping tension and preventing bunching. ",
                    "1.\tStarting at one end of your intended line, bring the needle up through the back to the front of the fabric. \n\n" +
                            "2.\tBring it back down one stitch length away down the line. \n\n" +
                            "3.\tLeaving some space between your stitches(I like to try and keep the space between stitches the same as the stitch length, but can be varied), repeat steps 1-2 until you achieve your desired line length. \n",
                    R.drawable.runningstitch_collage, R.drawable.runningstitch_final);
            mDao.insertStitch(runningStitch);

            Stitch lazyDaisy = new Stitch("Laisy Daisy",
                    "Flower",
                    "The lazy daisy is a cute floral stich. It is actually a series of detached chain stitches, arranged in a particular pattern to make a flower shape. We will be talking about a simple circular pattern here, but once you’ve mastered it, feel free to find varieties on the lazy daisy to suit your pattern! ",
                    "1.\tMark the center point of your flower. You will be working in a circle around it. \n\n" +
                            "2.\tEach petal will be a single detached chain stitch. \n\n" +
                            "3.\tAt the central point of your petal, bring the needle through to the front of the fabric.\n\n" +
                            "4.\tAs close as possible, but NOT through the same hole, push the needle back through to the back of the fabric. You want to leave a fairly large loop- do not pull it tight!\n\n" +
                            "5.\tBring your needle back up through to the front at the place you wish your petal to end, having your needle go up through the loop. \n\n" +
                            "6.\tNow you can pull your thread taught, although you may want to play with the tension to get the petal shape you were aiming for. \n\n" +
                            "7.\tTo finish your stitch, push your needle through to the back just on the other side of your loop thread to tack it into place. \n\n" +
                            "8.\tRepeat around the circle until you have all of your petals completed. \n\n" +
                            "9.\tFor an added flourish, you can add a French knot or other decoration in the center of your flower. \n",
                    R.drawable.laisydaisy_collage, R.drawable.laisydaisy_final);
            mDao.insertStitch(lazyDaisy);

            Stitch wovenWheelStitch = new Stitch("Woven Wheel Stitch",
                    "Flower",
                    "The woven wheel stitch is frequently used for floral designs, most often roses or similar rounded flowers. Given the depth of the stitch, they tend to really “pop,” and can really add some variety to your design. Plus, they are a lot of fun to stitch. For this one, the way you set it up really matters, so it is strongly recommended you have the pattern on your fabric beforehand. ",
                    "1.\tTo set the pattern, you will want to draw a circle that will be the outline of your wheel. You will then mark five equidistant lines extending from the center to the outer circle. It is important they be properly spaced, and have an odd number of lines (typically five). \n\n" +
                            "2.\tStarting from the center point, stitch each of your lines extending through the circle, but there is no need to outline the circle. \n\n" +
                            "3.\tOnce you have your line stitches in place, it’s time to get to weaving! \n" +
                            "4.\tBring your needle up through to the front of the fabric as near as possible to the center point. \n\n" +
                            "5.\tYou will then weave the thread in an over/ under pattern in a circle through your line threads. Keep the thread fairly taught, but watch out to make sure your flower is not bunching up too much. \n\n" +
                            "6.\tContinue your over/under weaving until you have filled the whole ring, and then bring your needle back to the back under the edge of the wheel to finish your stitch. \n",
                    R.drawable.wovenwheelstitch_collage, R.drawable.wovenwheelstitch_final);
            mDao.insertStitch(wovenWheelStitch);

            Stitch whippedBackStitch = new Stitch("Whipped Back Stitch",
                    "Outline", "The whipped back stitch is a fancier variation on a basic back stitch. It gives the stitch a more connected, twisting look. If you want to give your lines some “pop”, this is a great way to do it. Because you are stitching the line twice, you can use the same color both times or pick a contrasting color for the second pass!",
                    "1.\tFirst, you will stitch your entire line with a traditional back stitch as your base layer. Make sure your stitch length is long enough you can pass your needle underneath it! \n\n" +
                            "2.\tFor your second pass, you still start by bringing the needle from the back through to the front of the fabric at the beginning of your line. Importantly, it will stay in front of the fabric until the end of the line; you will not be stitching through the fabric for this part. \n\n" +
                            "3.\tStarting from the same side each time, slide the needle underneath each stitch so that it wraps around your original stitch line. Do not change directions! It needs to slide under the original stitches from the same side the entire time. \n\n" +
                            "4.\tContinue until you have gone down your whole line a second time, and then push the needle back through to the back of the fabric to finish off your stitch. \n",
                    R.drawable.whippedbackstitch_collage, R.drawable.whippedbackstitch_final);
            mDao.insertStitch(whippedBackStitch);

            Stitch herringboneStitch = new Stitch("Herringbone Stitch",
                    "Border",
                    "The herringbone stitch makes a lovely, crisscrossing border stitch. It’s lattice-like shape also makes it versatile for filling spaces or for leaf designs. ",
                    "1.\tYou will be working this stitch in between two lines. Make a small diagonal stitch that ends on your bottom line. \n\n" +
                    "2.\tBring the needle back up through the fabric on the bottom line parallel to your starting stitch. You are going to cross over and make an “x” with your first stitch, but over-extend so the crossing line goes all the way to the top of your border area. \n\n" +
                    "3.\tNow you will want to bring the needle back up through the fabric one stitch length behind where your last stitch ended, parallel to it on the top line of the border area. \n\n" +
                    "4.\tCreate another “x” stitch by crossing over your previous line and pushing the needle to the back on the bottom line. \n\n" +
                    "5.\tRepeat until you achieve your desired border length. \n", R.drawable.herringbone_collage, R.drawable.herringbone_final );
            mDao.insertStitch(herringboneStitch);

            Stitch chainStitch = new Stitch("Chain Stitch",
                    "Leaf",
                    "The chain stitch is a versatile stitch which can be modified for a number of uses. Shown here is a more traditional chain stitch in a continuous line. It is perfect when you want a thicker outline or even a border. It can also be used when doing floral designs when stitching stems or greenery. Our lazy daisy tutorial uses a modified version of this stitch- the detached chain stitch. ",
                    "1.\tBring your needle up through the fabric at the start of your line, then back down as close as possible to your start point without pulling it through the same hole. \n\n" +
                            "2.\tLeave a lot of slack- do not pull it tight! You are going to use that loop. \n\n" +
                            "3.\tBring the needle up through the fabric one stitch length away, pushing it through the loop you made earlier. Now pull the thread taught. \n" +
                            "4.\tPush the needle to the back of the fabric just next to where you brought it up through the loop (again being sure it does not go through the same hole). Here you are making the second loop in your chain. \n\n" +
                            "5.\tBring the thread up through the fabric one stitch length away through your new loop, and then pull the thread taught. \n\n" +
                            "6.\tRepeat until you achieve your desired line length. \n",
                    R.drawable.chainstitch_collage, R.drawable.chainstitch_final);

            mDao.insertStitch(chainStitch);
            return null;
        }
    }
}

